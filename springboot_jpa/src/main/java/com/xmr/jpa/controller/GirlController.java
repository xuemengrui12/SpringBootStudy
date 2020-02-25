package com.xmr.jpa.controller;

import com.xmr.jpa.domain.Girl;
import com.xmr.jpa.service.IGirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by xmr on 2018/3/14.
 */
@RequestMapping("/girls")
@RestController
public class GirlController {
    @Autowired
    private IGirlService girlService;

    @GetMapping
    public List<Girl> girlList() {
        return girlService.findAll();
    }

    /**
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/save")
    public Girl save(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setName(name);
        girl.setAge(age);
        return girlService.save(girl);
    }

    @ResponseBody
    @PostMapping(value = "/saveGirl")
    public String saveGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();

        } else {
            girlService.save(girl);
            return "添加成功！";
        }

    }

    @PostMapping(value = "/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    //更新
    @PutMapping(value = "/update/{id}")
    public Girl updateGirl(@PathVariable("id") Integer id, @RequestParam("name") String name,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setName(name);
        girl.setAge(age);
        return girlService.save(girl);
    }

    //查询
//    @GetMapping(value = "/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Girl> findGirl(@PathVariable("id") Integer id) {
        return girlService.findById(id);
    }

    //通过自定义的年龄查询
    @GetMapping(value = "/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age) {
        return girlService.findByAge(age);
    }

    //通过自定义的年龄查询
    @GetMapping(value = "/name/{name}")
    public List<Girl> findByName(@PathVariable("name") String name) {
        return girlService.findByName(name);
    }//通过自定义的年龄查询

    // 通过自定义的年龄查询
    @GetMapping(value = "/nameLike/{name}")
    public List<Girl> findByNameLike(@PathVariable("name") String name) {
        return girlService.findByNameLike(name);
    }//通过自定义的年龄查询

    @GetMapping(value = "/id/{id}")
    public Optional<Girl> findById(@PathVariable("id") Integer id) {
        return girlService.findById(id);
    }


    //删除
    @DeleteMapping(value = "/delete/{id}")
    public void deleteGirl(@PathVariable("id") Integer id) {
        girlService.deleteById(id);
    }


    @RequestMapping("/rollback")
    public Girl rollback(Girl person) { //测试回滚情况

        return girlService.saveGirlWithRollBack(person);
    }

    @RequestMapping("/norollback")
    public Girl noRollback(Girl person) {//测试不回滚情况

        return girlService.saveGirlWithoutRollBack(person);

    }
}
