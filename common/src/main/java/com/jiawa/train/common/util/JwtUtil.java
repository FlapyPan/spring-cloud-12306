package com.jiawa.train.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 盐值很重要，不能泄漏，且每个项目都应该不一样，可以放到配置文件中
     */
    private static final String key = "Jiawa12306";

    public static String createToken(Long id, String mobile) {
        LOG.info("开始生成JWT token，id：{}，mobile：{}", id, mobile);
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        DateTime now = DateTime.now();
        DateTime expTime = now.offsetNew(DateField.HOUR, 24);
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, expTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 内容
        payload.put("id", id);
        payload.put("mobile", mobile);
        String token = JWTUtil.createToken(payload, key.getBytes());
        LOG.info("生成JWT token：{}", token);
        return token;
    }

    public static boolean validate(String token) {
        LOG.info("开始JWT token校验，token：{}", token);
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        // validate包含了verify
        boolean validate = jwt.validate(0);
        LOG.info("JWT token校验结果：{}", validate);
        return validate;
    }

    public static JSONObject getJSONObject(String token) {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        LOG.info("根据token获取原始内容：{}", payloads);
        return payloads;
    }

    public static void main(String[] args) {
        createToken(1L, "123");

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE2NzY4OTk4MjcsIm1vYmlsZSI6IjEyMyIsImlkIjoxLCJleHAiOjE2NzY4OTk4MzcsImlhdCI6MTY3Njg5OTgyN30.JbFfdeNHhxKhAeag63kifw9pgYhnNXISJM5bL6hM8eU";
        validate(token);

        getJSONObject(token);
    }
}
