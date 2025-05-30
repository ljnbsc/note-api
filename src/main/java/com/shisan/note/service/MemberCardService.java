package com.shisan.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.dto.member.MemberCardRegisterDto;
import com.shisan.note.entity.member.MemberCard;

public interface MemberCardService extends IService<MemberCard> {

    /**
     * 会员卡注册
     */
    void register(MemberCardRegisterDto registerDto);

}
