package com.xiong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.mapper.PostMapper;
import com.xiong.pojo.Post;
import com.xiong.pojo.User;
import com.xiong.pojo.vo.PostIdParam;
import com.xiong.pojo.vo.UserIdParam;
import com.xiong.service.PostService;
import com.xiong.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public R creatPost(Post post) {

        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",post.getTitle());
        //数据库查询 (查询数量 >0 存在)
        Integer total = postMapper.selectCount(queryWrapper);
        if (total > 0){
            log.info("PostServiceImpl.creatPost业务结束,结果{}","文章题目已经存在");
            return R.fail("文章题目已经存在不可重复创建");
        }

        int rows = postMapper.insert(post);
        if (rows == 0){
            log.info("PostServiceImpl.creatPost业务结束,结果{}","数据插入失败,文章添加失败!");
            return R.fail("文章添加失败,请稍后再试!");
        }

        log.info("PostServiceImpl.creatPost业务结束,结果{}","文章创建成功!");
        return R.ok("创建成功");

    }


    public List<Post> searchUserPostById(Integer uid,Integer flag){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        if (flag==0){
            queryWrapper.eq("user_id",uid).orderByDesc("created");
        }else {
            queryWrapper.eq("user_id",uid).orderByAsc("created");
        }
        List<Post> posts = postMapper.selectList(queryWrapper);

        log.info("PostServiceImpl.searchUserPostById业务结束,结果{}","查询成功");
        return posts;
    }

    public R searchPostById(Integer id){
        Post post = postMapper.selectById(id);
        if (post == null){
            R.fail("查询失败或没有数据");
        }
        log.info("PostServiceImpl.searchPost业务结束,结果{}","查询成功");
        return R.ok("通过文章ID文章查询成功",post);
    }


    @Override
    public R updatePostById(Integer id,Post post) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",id);
        //数据库查询
        Integer total = postMapper.selectCount(queryWrapper);
        if (total == 0){
            log.info("PostServiceImpl.updatePostById业务结束,结果{}","需要更新的文章不存在");
            return R.fail("需要更新的文章不存在");
        }

        int rows = postMapper.update(post, queryWrapper);

        if (rows == 0){
            log.info("PostServiceImpl.updatePostById业务结束,结果{}","数据插入失败,文章添加失败!");
            return R.fail("文章更新失败,请稍后再试!");
        }

        log.info("PostServiceImpl.updatePostById业务结束,结果{}","文章修改成功!");
        return R.ok("文章更新成功");
    }

    @Override
    public R deletePostById(Integer id) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",id);
        //数据库查询
        Integer total = postMapper.selectCount(queryWrapper);
        if (total == 0){
            log.info("PostServiceImpl.deletePostById业务结束,结果{}","需要删除的文章不存在");
            return R.fail("需要删除的文章不存在");
        }

        int rows = postMapper.delete(queryWrapper);

        if (rows == 0){
            log.info("PostServiceImpl.deletePostById业务结束,结果{}","文章删除失败!");
            return R.fail("文章删除失败,请稍后再试!");
        }

        log.info("PostServiceImpl.deletePostById业务结束,结果{}","文章删除成功!");
        return R.ok("文章删除成功");


    }


}
