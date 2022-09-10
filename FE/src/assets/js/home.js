import User from '../../services/user';
import Highcharts from 'highcharts';

document.body.classList.add('home');
export default {
	name: 'HomePage',
	mounted(){
			this.getOrders();
	},
	data(){
		return {
			orderHistory:[]
		}
	},
	methods:{
		async getOrders(){
		let vm = this;
		let userData = localStorage.userData;
		if(!userData) return;
		let user = JSON.parse(userData);
		let chartData={x:[],sData:{}, series:[]}
		await User.userOrders(user).then((data)=>{
				vm.orderHistory = data.orders; 
				vm.orderHistory.map((i)=>{
					chartData.x.push(i.placed);
					delete i.mealeculeQuotientData.energy
					for( let k in i.mealeculeQuotientData){
						if(vm.$root.preferredMealecule.indexOf(k)==-1) continue;
						if (k in chartData.sData)
							chartData.sData[k].push(Math.round(i.mealeculeQuotientData[k]))
						else
							chartData.sData[k] =[Math.round(i.mealeculeQuotientData[k])]
					}
				})
				for (let k in chartData.sData){
					let data = chartData.sData[k]
					chartData.series.push({
						name : k,
						data:data
					})
				}
		});
		Highcharts.chart('orderGraph', {
				chart: {
					type: 'column'
			},
			title: {
					text: 'purchase mq history'
			},
			yAxis:{
					visible:false
			},
			xAxis:{
				visible: false,
				type: 'datetime',
				categories: chartData.x
			},
			legend:{
				enabled:false
			},
			series: chartData.series
		});
	}
	}
}