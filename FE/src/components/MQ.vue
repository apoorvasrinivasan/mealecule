<template>

<div class="mq">
   <svg width="100%" height="100%" viewBox="0 0 40 39" class="donut">
    <circle class="donut-hole" cx="20" cy="20" r="15.91549430918954" fill="#fff"></circle>
    <circle class="donut-ring" cx="20" cy="20" r="15.91549430918954" fill="transparent" stroke-width="3.5"></circle>
     <circle v-for="(item, index) in prefered_mq" :key="index" :class="'donut-segment donut-segment-'+index" cx="20" cy="20" r="15.91549430918954" fill="transparent" stroke-width="3.5" :stroke-dasharray="item.value + ' ' + (100-item.value)" :data-key="item.key"  :stroke = "item.color" :stroke-dashoffset="item.offset"></circle>
   
    
    <g class="donut-text donut-text-2">

      <text y="50%" transform="translate(0, 2)">
        <tspan x="50%" text-anchor="middle" class="donut-percent">{{ nutrients.cal }}</tspan>   
      </text>
      <text y="60%" transform="translate(0, 2)">
        <tspan x="50%" text-anchor="middle" class="donut-data">kCal</tspan>   
      </text>
    </g>
  </svg>

<div class="mq-data">
<template v-for="(item, index) in mqdata" :key="index">
    <span :style="'border-left-color:'+item.color"> {{ item.key }}</span>
    <span> {{  item.value }}g</span>
</template>
  <span>Calories </span>
  <span> {{  nutrients.cal }}K</span>
    
 </div>
</div>
</template>
<script>

export default {
  name: 'MQ',
    props: {
      nutrients: Object
  },
  data(){
    return {
      mqdata:[],
      prefered_mq:[]
    }
  },
  created() {
    // props are exposed on `this`
    let total = 0;
    let pm = this.$root.preferredMealecule;
    for (let i in this.nutrients.chart){
        let j = this.nutrients.chart[i];
        if(pm.indexOf(j.key) > -1) {
            j.show = true;
            j.offset = -1 * (total);
            total += j.value; 
            j.color = this.$root.colors[pm.indexOf(j.key)]
        }
        this.mqdata.push(j);
    }
    this.prefered_mq = this.mqdata.filter(i=>{
        return i.show;
    })
  }
}
</script>
<style scoped src="@/assets/css/mq.css">

</style>