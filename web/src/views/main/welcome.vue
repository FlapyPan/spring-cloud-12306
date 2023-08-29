<template>
  <!--  <p style="font-size: 20px; font-weight: bold">欢迎使用甲蛙12306售票系统</p>-->
  <div style="border: 4px solid orange;
              border-radius: 4px;
              padding: 10px; ">
    <h3>
      <b>示例网站说明（网站实现的功能课程都有手把手教）</b>
    </h3>
    本项目共有15张业务表，会手把手教自制通用代码生成器，快速生成增删改查包含界面，减少无意义的增删改查占用课程时间。<br>
    购票流程看起来简单，但用了很多看不见的高并发技术，比如10万人抢1000张票：<br>
    <li>利用<b>CDN</b>，提高用户访问页面速度</li>
    <li>利用<b>分布式缓存</b>，在秒杀开始前，提供高性能余票查询，同时要考虑缓存击穿、穿透、雪崩等问题</li>
    <li>使用<b>第一层验证码</b>，纯前端验证码在前端削弱瞬时高峰，将100毫秒内10万人的请求，分散成1~2秒内10万人请求</li>
    <li>使用<b>第二层验证码</b>，后端验证码，进一步分散请求，同时防止机器人抢票</li>
    <li>使用<b>限流技术</b>减轻无谓请求，同时给用户快速失败响应（告知票没有了），将9万请求快速失败，变成1万请求抢1000张票</li>
    <li>使用<b>令牌发放技术</b>，控制抢票量，同时防止机器人刷票，比如开放2000令牌，即变成8000请求快速失败，变成2000请求抢1000张票</li>
    <li>使用<b>分布式锁技术</b>，防止超卖，即2000人抢1000张票，最终只能卖出1000张，不能卖出1001张票</li>
    <li>使用<b>异步削峰+排队机制</b>，解决吞吐量问题，实现最短时间内给用户反馈，1000请求告知票没有了，1000请求告知排队中</li>
    <li>使用<b>分布式事务</b>，保证数据最终一致性，不能库存减少了，票却没打出来。</li>
  </div>
  <a-divider style="border-color: #7cb305" dashed />
  <div style="border: 4px solid #99CCFF;
              border-radius: 4px;
              padding: 10px; ">
    <h3>
      <b>示例网站只开放会员端功能，控台管理暂不开放（怕被乱改车次数据，影响体验）</b>
    </h3>
    <div>
      如果您还没有乘车人，可以先去
      <router-link to="/passenger">
        添加乘车人
      </router-link>
    </div>
    <div>
      购票功能支持选座，去
      <router-link to="/ticket">
        查询余票并购票
      </router-link>
      。购票成功后，可以查看
      <router-link to="/my-ticket">
        我的车票
      </router-link>
      ；去
      <router-link to="/seat">
        座位销售图
      </router-link>
      查看每个座位的销售情况
    </div>
    <div style="font-size: 14px; color: red">
      本项目还有一套控台管理模块，用于维护车次数据、调度任务，查看会员、车票等功能，怕有同学乱改数据，暂不开放体验
    </div>
    <div style="font-size: 14px; color: #888888">
      小提示：为了方便大家体验，本项目没对重复购票做校验，即：同一个人可重复买同一个车次的车票
    </div>
  </div>

  <a-divider style="border-color: #7cb305" dashed />
  <div style="border: 4px solid orange;
              border-radius: 4px;
              padding: 10px; ">
    <h3>
      <b>本项目的配套视频课程：
        <a href="https://coding.imooc.com/class/641.html" target="_blank">
          《Springboot3+微服务实战12306高性能售票系统》
        </a>
      </b>
    </h3>
    <div>
      慕课网明星讲师“甲蛙”全新力作：一套市面稀缺的，手把手教你完整落地“高并发，高可用，高性能” 整体解决方案实战课。课程以12306售票系统驱动教学，融合老师多年大厂超大型项目架构设计与实战经验，带你从架构设计到具体场景方案落地，真正驾驭超高并发场景下的各种疑难问题，成为高薪抢手人才。
    </div>
  </div>
  <a-divider style="border-color: #7cb305" dashed />
  <div style="border: 4px solid #99CCFF;
              border-radius: 4px;
              padding: 10px; ">
    <h3>
      <b>甲蛙老师简介，公众号：甲蛙全栈
      </b>
    </h3>
    <div>
      十五年Java研发和带团队经验，九年第三方支付工作经验，负责公司核心支付项目的研发管理工作及技术培训工作，多年的支付相关工作造就了严谨的代码开发风格。专注于Java全栈技术研究和分享，独立完成过多个项目，熟悉项目开发的生命周期，从框架搭建到部署上线。讲课风格以实战为基础，不说废话，只讲干货。
      <br>
      <br>
      慕课网明星讲师，共有四门课：<br>
      <div>
        实战课：
        <a href="https://coding.imooc.com/class/641.html" target="_blank">
          《Springboot3+微服务实战12306高性能售票系统》
        </a>
        难度：<star-outlined /><star-outlined /><star-outlined />
      </div>
      <div>
        实战课：
        <a href="https://coding.imooc.com/class/474.html" target="_blank">
          《Spring Boot+Vue3前后端分离，实战wiki知识库系统》
        </a>
        难度：<star-outlined />
      </div>
      <div>
        实战课：
        <a href="https://coding.imooc.com/class/416.html" target="_blank">
          《Spring Cloud+Vue前后端分离开发企业级在线视频系统》
        </a>
        难度：<star-outlined /><star-outlined />
      </div>
      <div>
        免费课：
        <a href="http://www.imooc.com/learn/1160" target="_blank">
          《开发工具IDEA从入门到爱不释手》
        </a>
        难度：<star-outlined />
      </div>
    </div>
  </div>
</template>

<script>

import { defineComponent } from 'vue';

export default defineComponent({
  name: "welcome-view",
  setup() {
    return {
    };
  },
});
</script>
