<template lang='pug'>
div.myaccount
 h1.ui.header Welcome, {{ user.firstName }}
 button.ui.tiny.primary.button(v-on:click='logout()') Logout
 div.ui.cards.accounts-cards
    div.ui.card
      h2.ui.header.teal Current Progress
      div.ui.statistics
        div.ui.statistic
          span.value 
            i.ui.huge.label.circular(:class="(coins>0)?'bCoins':'disabled'") $
          span.label {{ coins || 0 }} Coins
        div.ui.statistic
          span.value 
            i.ui.huge.label.circular.badges(:class="badges")
              i.icon.ui.trophy(:class="{'disabled':!badges}")
          span.label {{ badges || 'no' }} Badge
      router-link.ui.primary.button(to="/game") Earn more coins
    div.ui.card.checkbox
      h2.ui.header.teal My Preferred Mealecules
      form.ui.form.preffered_mq
        div.ui.field(v-for="m in MQ_list")
          div.ui.checkbox
          input(type="checkbox" :id="'mq_'+m" v-model = "user_pm" :value="m" :disabled="user_pm.length>=4 && user_pm.indexOf(m) == -1") 
          label(:for="'mq_'+m" ) {{m}}
      button.ui.primary.fluid.button(v-on:click="savePM()" :disabled="form_disabled || loaders.pm" :class="{'loading':loaders.pm}" ) 
        | Save   
        div.ui.circular.label.tiny.bCoins 10

    div.ui.card.userinfo
      h2.ui.header.teal Update Information
      form.ui.form
        div.ui.field
          label Weight (in kg)
          input(v-model="user.weight")
        div.ui.field
          label Height (in cm)
          input(v-model="user.height")
        div.ui.field
          label Age
          input(v-model="user.age")
        button.ui.primary.fluid.button(:disabled="inform_disabled || loaders.info" v-on:click="saveVitals()" :class="{'loading':loaders.info}") 
          | Save   
          div.ui.circular.label.tiny.bCoins 20
          
    div.ui.card(v-if="badges")
      div.ui.top.attached.label(:class="badges") {{ badges }}
      h2.ui.header.teal BMI Calculator
      form.ui.form
        div.ui.field
          label Weight (in kg)
          input(v-model="bmi.w")
        div.ui.field
          label Height (in cm)
          input(v-model="bmi.h")
        button.ui.primary.fluid.button(v-on:click="calcBMI()") Calculate BMI
        div.bmi_result.ui.message(v-if="bmi.result") {{ bmi.result }}
 section#orderHistory.orders(v-if="orderHistory.length>0")
    h2.ui.header.teal Order History
    table.ui.table
      thead
        tr
          th Mealecule Quotient
          th Energy
          th Price
          th Status
          th Date
      tbody
        tr(v-for="o in orderHistory")
          td 
              span.ui.tiny.label(v-for="k,v in o.mealeculeQuotientData") {{ Math.round(k) }}g: {{ v}}
          td 
            span.ui.tiny.label.teal {{ Math.round(o.energy) }} kCal
          td 
            span.price {{o.total.value}}
          td {{o.status}}
          td {{o.placed.toDateString() }}
 section#orders(v-if="orderHistory.length>0")
 div.ui.message(v-else) You have no orders yet.
  
</template>
<script type="text/javascript" src="@/assets/js/myAccount.js">

</script>
<style scoped>
.ui.cards.accounts-cards{
  display: flex;
  flex-wrap: nowrap;
}
.ui.cards{
  margin-top: 24px;
}
.ui.cards > .card {
  padding:  24px;
}
.ui.label.BRONZE{
  color: #f4f4f4;
  background-color: var(--bronze);
}
.preffered_mq {
  display: grid;
  grid-template-columns: 50% 50%;
}
.preffered_mq.ui.form .field>label {
  font-weight: 400;
  text-transform: capitalize;
}
.ui.button .bCoins{
  margin-left: 10px;
}
#orderHistory, #orders {
  margin-top: 24px;
}
.ui.card>.button, .ui.card>.buttons, .ui.cards>.card>.button, .ui.cards>.card>.buttons {
  margin-top: auto;
}
@media screen and (max-width:800px){
  .ui.cards.accounts-cards{
    display: grid;
    grid-template-columns: 90%;
    justify-content: center;
  }
  .ui.card, .ui.cards>.card {
    width:  90%;
  }
}
</style>