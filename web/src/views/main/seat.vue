<template>
  <div v-if="!param.date">
    请到余票查询里选择一趟列车，
    <router-link to="/ticket">
      跳转到余票查询
    </router-link>
  </div>
  <div v-else>
    <p style="font-weight: bold;">
      日期：{{param.date}}，车次：{{param.trainCode}}，出发站：{{param.start}}，到达站：{{param.end}}
    </p>

    <table>
      <tr>
        <td style="width: 25px; background: #FF9900;"></td>
        <td>：已被购买</td>
        <td style="width: 20px;"></td>
        <td style="width: 25px; background: #999999;"></td>
        <td>：未被购买</td>
      </tr>
    </table>
    <br>
    <div v-for="(seatObj, carriage) in train" :key="carriage"
         style="border: 3px solid #99CCFF;
                 margin-bottom: 30px;
                 padding: 5px;
                 border-radius: 4px">
      <div style="display:block;
                  width:50px;
                  height:10px;
                  position:relative;
                  top:-15px;
                  text-align: center;
                  background: white;">
        {{carriage}}
      </div>
      <table>
        <tr>
          <td v-for="(sell, index) in Object.values(seatObj)[0]" :key="index"
              style="text-align: center">
            {{index + 1}}
          </td>
        </tr>
        <tr v-for="(sellList, col) in seatObj" :key="col">
          <td v-for="(sell, index) in sellList" :key="index"
              style="text-align: center;
                      border: 2px solid white;
                      background: grey;
                      padding: 0 4px;
                      color: white;
                      "
              :style="{background: (sell > 0 ? '#FF9900' : '#999999')}">{{col}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>

import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";
import {notification} from "ant-design-vue";
import {useRoute} from "vue-router";

export default defineComponent({
  name: "seat-view",
  setup() {
    const route = useRoute();
    const param = ref({});
    param.value = route.query;
    const list = ref();
    // 使用对象更便于组装数组，三维数组只能存储最终的01，不能存储“车箱1”，“A”这些数据
    // {
    //   "车箱1": {
    //      "A" : ["000", "001", "001", "001"],
    //      "B" : ["000", "001", "001", "001"],
    //      "C" : ["000", "001", "001", "001"],
    //      "D" : ["000", "001", "001", "001"]
    //    }, "车箱2": {
    //      "A" : ["000", "001", "001", "001"],
    //      "B" : ["000", "001", "001", "001"],
    //      "C" : ["000", "001", "001", "001"],
    //      "D" : ["000", "001", "001", "001"],
    //      "D" : ["000", "001", "001", "001"]
    //    }
    // }
    let train = ref({});

    // 查询一列火车的所有车站
    const querySeat = () => {
      axios.get("/business/seat-sell/query", {
        params: {
          date: param.value.date,
          trainCode: param.value.trainCode,
        }
      }).then((response) => {
        let data = response.data;
        if (data.success) {
          list.value = data.content;
          format();
        } else {
          notification.error({description: data.message});
        }
      });
    };

    /**
     * 截取出当前区间的销售信息，并判断是否有票
     */
    const format = () => {
      let _train = {};

      for (let i = 0; i < list.value.length; i++) {
        let item = list.value[i];

        // 计算当前区间是否还有票，约定：站序是从0开始
        let sellDB = item.sell;

        // 假设6站：start = 1, end = 3, sellDB = 11111，最终得到：sell = 01110，转int 1100，不可买
        // 假设6站：start = 1, end = 3, sellDB = 11011，最终得到：sell = 01010，转int 1000，不可买
        // 假设6站：start = 1, end = 3, sellDB = 10001，最终得到：sell = 00000，转int 0，可买
        // 验证代码：
        // let sellDB = "123456789";
        // let start = 1;
        // let end = 3;
        // let sell = sellDB.substr(start, end - start)
        // console.log(sell)
        let sell = sellDB.substr(param.value.startIndex, param.value.endIndex - param.value.startIndex);
        // console.log("完整的销卖信息：", sellDB, "区间内的销卖信息", sell);

        // 将sell放入火车数据中
        if (!_train["车箱" + item.carriageIndex]) {
          _train["车箱" + item.carriageIndex] = {};
        }
        if (!_train["车箱" + item.carriageIndex][item.col]) {
          _train["车箱" + item.carriageIndex][item.col] = [];
        }
        _train["车箱" + item.carriageIndex][item.col].push(parseInt(sell));
      }

      train.value = _train;
    }

    onMounted(() => {
      if (param.value.date) {
        querySeat();
      }
    });

    return {
      param,
      train
    };
  },
});
</script>
