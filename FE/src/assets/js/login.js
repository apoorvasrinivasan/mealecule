import User from '../../services/user'
import Common from '../../services/common'

export default {
  name: 'UserLogin',
  data(){
    return {
      reg_success: false,
      log_error: '',
      reg_error: '',
      passerror:false,
      ulogin:{
        uid:'test19919@yahoo.com',
        password:'Test@123'
      },
      user:{
        titleCode:'mr',
        uid:'',
        password:'',
        firstName:'',
        lastName:''
      },
      confirmpassword:''
    }
  },
  methods:{
    checkpassword(){
      let vm = this;
      vm.passerror = false;
      if (vm.user.password.trim().length > 0 &&  vm.confirmpassword.trim().length > 0 && vm.user.password != vm.confirmpassword)
        vm.passerror = true;
    },
    register(){
      let vm = this;
      vm.reg_error = '';
      for (let i in vm.user){
        if( vm.user[i].trim().length == 0){
          vm.reg_error = i + ' is required';
          return;
        }
      }
      User.registerUser(vm.user, ()=>{
        Common.Alert('user registered. please login');
        User.addRegCoins(50, vm.user.uid);
        vm.ulogin.uid = vm.user.uid;
      }, (r)=>{
        vm.reg_error = r.errors[0].message;
         console.log(r)
      })
    },
    login: function(){
      let vm = this;
      vm.log_error = '';
      for (let i in vm.ulogin){
        if( vm.ulogin[i].trim().length == 0){
          vm.log_error = i + ' is required';
          return;
        }
      }
      User.loginUser(vm.ulogin, (data)=>{
        localStorage.setItem('user',vm.ulogin.uid);
        let userData = {
          uid: data.uid,
          name: data.name,
          firstName: data.firstName,
          gameData: data.gameData,
          preferredMealecule: data.preferredMealecule,
          weight:data.gameData.weight,
          height:data.gameData.height,
          age:data.gameData.age,
        }
        localStorage.setItem('userData',JSON.stringify(userData));
        vm.$root.isLogged = true;
        vm.$root.preferredMealeacule = data.preferredMealeacule;
        vm.$root.total_coins = data.gameData.coins;
        User.createCart(()=>{
            vm.$root.cart = 0;
          vm.$router.push('/categories');
        })
      }, (r)=> {
        vm.log_error = r.errors[0].message;
  
      })
    }
  }
}