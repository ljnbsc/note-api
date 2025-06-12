package com.shisan.note.service.note;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.dto.note.NotebookDto;
import com.shisan.note.entity.note.Notebook;

import java.util.List;

/**
 * 笔记本
 * date 2025/6/12
 *
 * @author tuota
 */
public interface NotebookService extends IService<Notebook> {


    /**
     * 笔记本添加
     */
    void add(NotebookDto notebookDto);

    /**
     * 笔记本修改
     */
    void update(NotebookDto notebookDto);

    /**
     * 笔记本删除
     */
    void delete(Long id);

    /**
     * 获取用户笔记本
     */
    List<Notebook> getUserNotebook();


}
