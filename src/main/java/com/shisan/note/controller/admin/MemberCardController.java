package com.shisan.note.controller.admin;

import cn.shisan.common.domain.common.JResult;
import com.shisan.note.controller.BaseController;
import com.shisan.note.dto.member.MemberCardRegisterDto;
import com.shisan.note.dto.member.MemberCardTransactionDto;
import com.shisan.note.service.MemberCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags = "会员卡管理")
@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberCardController extends BaseController {

    private final MemberCardService memberCardService;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public JResult<Void> register(@RequestBody MemberCardRegisterDto registerDto) {
        memberCardService.register(registerDto);
        return success();
    }

    @ApiOperation("会员充值")
    @PostMapping("/recharge")
    public JResult<Void> recharge(@RequestBody MemberCardTransactionDto transactionDto) {
        memberCardService.recharge(transactionDto);
        return success();
    }

    @ApiOperation("会员消费")
    @PostMapping("/consume")
    public JResult<Void> consume(@RequestBody MemberCardTransactionDto transactionDto) {
        memberCardService.consume(transactionDto);
        return success();
    }

}
