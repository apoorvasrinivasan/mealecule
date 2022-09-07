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
<style scoped>
.svg-item {
    width: 100%;
    font-size: 16px;
    margin: 0 auto;
    animation: donutfade 1s;
}

@keyframes donutfade {
  /* this applies to the whole svg item wrapper */
    0% {
        opacity: .2;
    }
    100% {
        opacity: 1;
    }
}

@media (min-width: 992px) {
    .svg-item {
        width: 80%;
    }
}

.donut-ring {
    stroke: #EBEBEB;
}


.donut-segment-0 {
    /*stroke: #A0DD9A;*/
    animation: donut2 3s;
}

.donut-segment-1 {
    /*stroke: #4CBE41;*/
    animation: donut2 3s;
}

.donut-segment-2 {
    /*stroke: #2C737E;*/
    animation: donut2 3s;
}
.donut-segment-3 {
    /*stroke: #BFE3E9;*/
    animation: donut2 3s;
}



.donut-percent {
    animation: donutfadelong 1s;
}

@keyframes donutfadelong {
    0% {
        opacity: 0;
    }
    100% {
        opacity: 1;
    }
}

@keyframes donut2 {
    0% {
        stroke-dasharray: 0, 100;
    }
    50% {
        stroke-dasharray: 60, 40;
    }
}



.donut-text {
    font-family: Arial, Helvetica, sans-serif;
    fill: #FF6200;
}

.donut-text-2 {
    fill: #d9e021;
}
.donut-label {
    font-size: 0.28em;
    font-weight: 700;
    line-height: 1;
    fill: #000;
    transform: translateY(0.25em);
}

.donut-percent {
    font-size: 0.5em;
    line-height: 1;
    transform: translateY(0.5em);
    font-weight: bold;
}

.donut-data {
    font-size: 0.2em;
    line-height: 1;
    transform: translateY(0.5em);
    text-align: center;
    text-anchor: middle;
    color:#666;
    fill: #666;
    animation: donutfadelong 1s;
}




/* ---------- */
/* just for this presentation */
html { text-align:center; }
.svg-item {
  max-width:30%;
  display:inline-block;
}

.mq:hover > .mq-data{
    display: grid;
}
.mq-data {
    /*grid-gap: 2px;*/
    background: #fff;
    box-shadow: 0 0 1px 2px #ccc;
    border-radius: 4px;
    color: var(--red);
    display: none;
    font-size: 10px;
    grid-template-columns: auto 20px;
    justify-content: space-between;
    text-indent: 5px;
    margin: 0 auto;
    max-width: fit-content;
    padding: 5px 15px 5px 3px;
    position: absolute;
    top:  0;
    width:  95%;
}
.mq-data span:nth-child(2n-1) {
    overflow: hidden;
    text-overflow: ellipsis;
    text-transform: capitalize;
    border-left:  3px solid transparent;
}
</style>