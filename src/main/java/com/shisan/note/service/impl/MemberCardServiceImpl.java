package com.shisan.note.service.impl;

import cn.shisan.common.exception.BusinessException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shisan.note.common.enums.MemberCardEnums;
import com.shisan.note.dto.member.MemberCardRegisterDto;
import com.shisan.note.dto.member.MemberCardTransactionDto;
import com.shisan.note.entity.member.MemberCard;
import com.shisan.note.entity.member.MemberTransactionRecord;
import com.shisan.note.mapper.member.MemberCardMapper;
import com.shisan.note.mapper.member.MemberTransactionRecordMapper;
import com.shisan.note.service.MemberCardService;
import com.shisan.note.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberCardServiceImpl extends ServiceImpl<MemberCardMapper, MemberCard> implements MemberCardService {

    private final MemberTransactionRecordMapper memberTransactionRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(MemberCardRegisterDto registerDto) {
        AssertUtils.isBlank(registerDto.getName(), "会员名称不能为空");
        AssertUtils.isBlank(registerDto.getPhone(), "会员手机号不能为空");
        AssertUtils.isNull(registerDto.getRemainingAmount(), "会员注册开卡金额不能为空");

        MemberCard memberCard = new MemberCard();
        memberCard.setAccount(registerDto.getPhone());
        memberCard.setName(registerDto.getName());
        memberCard.setPhone(registerDto.getPhone());
        memberCard.setGender(registerDto.getGender());
        memberCard.setBirthday(registerDto.getBirthday());
        memberCard.setRemainingAmount(registerDto.getRemainingAmount());
        memberCard.setStatus(MemberCardEnums.Status.ACTIVATE.getCode());
        memberCard.setOperator(registerDto.getOperator());
        memberCard.setCreated(LocalDateTime.now());
        int insert = baseMapper.insert(memberCard);
        if (insert <= 0) {
            throw new BusinessException("会员卡添加失败!");
        }

        MemberTransactionRecord transactionRecord = new MemberTransactionRecord();
        transactionRecord.setMemberCardId(memberCard.getId());
        transactionRecord.setTransactionType(MemberCardEnums.TransactionType.RECHARGE.getCode());
        transactionRecord.setAmount(registerDto.getRemainingAmount());
        transactionRecord.setRemainingAmount(registerDto.getRemainingAmount());
        transactionRecord.setRemark("注册开卡");
        transactionRecord.setOperator(registerDto.getOperator());
        transactionRecord.setCreated(LocalDateTime.now());
        int tr = memberTransactionRecordMapper.insert(transactionRecord);
        if (tr <= 0) {
            throw new BusinessException("会员卡添加失败!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recharge(MemberCardTransactionDto rechargeDto) {
        MemberCard memberCard = baseMapper.selectById(rechargeDto.getId());
        AssertUtils.isNull(memberCard, "会员不存在");

        BigDecimal remainingAmount = memberCard.getRemainingAmount();
        BigDecimal sum = remainingAmount.add(rechargeDto.getAmount());
        int updated = baseMapper.updateRemainingAmount(memberCard.getId(), sum, memberCard.getVersion());
        if (updated <= 0) {
            throw new BusinessException("会员卡充值失败!");
        }

        // 交易记录
        transaction(rechargeDto, MemberCardEnums.TransactionType.RECHARGE, sum);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void consume(MemberCardTransactionDto rechargeDto) {
        MemberCard memberCard = baseMapper.selectById(rechargeDto.getId());
        AssertUtils.isNull(memberCard, "会员不存在");
        BigDecimal remainingAmount = memberCard.getRemainingAmount();
        BigDecimal subtract = remainingAmount.subtract(rechargeDto.getAmount());
        int updated = baseMapper.updateRemainingAmount(memberCard.getId(), subtract, memberCard.getVersion());
        if (updated <= 0) {
            throw new BusinessException("会员卡消费失败!");
        }

        // 交易记录
        transaction(rechargeDto, MemberCardEnums.TransactionType.CONSUME, subtract);
    }

    /**
     * 交易记录
     */
    private void transaction(MemberCardTransactionDto rechargeDto,
                             MemberCardEnums.TransactionType transactionType,
                             BigDecimal remainingAmount) {
        MemberTransactionRecord transactionRecord = new MemberTransactionRecord();
        transactionRecord.setMemberCardId(rechargeDto.getId());
        transactionRecord.setTransactionType(transactionType.getCode());
        transactionRecord.setAmount(rechargeDto.getAmount());
        transactionRecord.setRemainingAmount(remainingAmount);
        transactionRecord.setRemark(rechargeDto.getRemark());
        transactionRecord.setOperator(rechargeDto.getOperator());
        transactionRecord.setCreated(LocalDateTime.now());
        int tr = memberTransactionRecordMapper.insert(transactionRecord);
        if (tr <= 0) {
            throw new BusinessException("会员交易失败!");
        }
    }
}
