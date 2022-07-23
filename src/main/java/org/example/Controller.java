package org.example;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
public class Controller {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get")
    public String get() {
        Stream<MyBean> objectStream = jdbcTemplate.queryForStream("SELECT * FROM xxl_job_group", new BeanPropertyRowMapper<>(MyBean.class));
        Optional<MyBean> first = objectStream.findFirst();
        System.out.println("--------------------------------------------- "+first.orElse(null));
        objectStream.close();
        return UUID.randomUUID().toString();
    }

    @Data
    public static class MyBean {
        private String app_name;
    }
}
