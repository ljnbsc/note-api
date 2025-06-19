package com.shisan.note.service.note;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shisan.note.entity.note.Diary;
import com.shisan.note.mapper.note.DiaryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日记，每日一记 服务实现类
 * </p>
 *
 * @author lijing
 * @since 2025-06-19
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {

}
