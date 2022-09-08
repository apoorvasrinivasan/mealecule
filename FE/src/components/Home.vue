<template lang="pug">
div.home-page
	h1.title How 
		b healthy 
		| is your cart
	section#game.game
		div.ui.two.column.grid
			div.column
				h2.ui.header.teal Current Progress
				div.ui.statistics
					div.ui.statistic
						span.value 
							i.ui.huge.label.circular.bCoins $
						span.label {{ $root.total_coins }} Coins
					div.ui.statistic(v-if="$root.badges")
						span.value 
							i.ui.huge.label.circular.badges(:class="$root.badges")
								i.icon.ui.trophy 
						span.label {{ $root.badges }} Badge
				div#orderGraph
				router-link.ui.button.primary.cta-button(to="/game") Play Game
			div.column.ui.image
				img(:src="require('@/assets/ame.jpg')")
	section#offer
			h2.ui.header.teal What we offer
			div.offers-cards
				div.offer-card
						div.image.games(aria-hidden="true")
						div.ui.header.tiny Play Games
						p Play a simple single player game to help Popeye win coins and avoid foodie obstacles midway. Also collect interesting badges and unlock exciting benefits, tto help you purchase your favourite products on site.

				div.offer-card
						div.image.earn-coins(aria-hidden="true")
						div.ui.header.tiny Earn Coins
						p Keep collecting BCoins on site by activating your user profile, setting essential body vitals and your peference of MQ to help us serve you better. Redeem these coins during purchase and win exciting discounts on all your favourite products.
				div.offer-card
						div.image.mq
						div.ui.header.tiny(aria-hidden="true") Mealecular Quotient
						p Understand the impact of buying a product by judging its Mealecular Quotient (MQ) - an info-metrics containing information in a visual representation, of quantities (%) of protein, carbohydrate, fat, fiber and caloric values for all products viewed and added to your cart. Sort and filter based on your preferred MQ.
				div.offer-card
						div.image.discount(aria-hidden="true")
						div.ui.header.tiny Get Discounts
						p End of a level in our game helps you win coins achievement badges. Earning coins leads to earning fun and interesting badges which would provide amazing discounts on MRP of all products being bought by you. Bronze badge (achieved at 100 coins) gives you 5% discount on MRP of products
	section#categories.categories
			div.glutenFree.cat-images
				div.ui.header Gluten Free
			div.vegan.cat-images
				div.ui.header Vegan
			div.childcare.cat-images
				div.ui.header Childcare
	section#categoryList
			h2.ui.header.teal Online Supermarket
			div.ui.four.cards
					router-link.ui.card(to="/plp/biscuits")
						div.image
							img(src="https://us.123rf.com/450wm/karidesigner/karidesigner2104/karidesigner210400049/168154799-chocolate-chip-cookie-in-cartoon-style-vector-illustration.jpg")
						div.content
							div.header Buscuits
					router-link.ui.card(to="/plp/chocolates")
						div.image
							img(src="https://img.freepik.com/premium-vector/illustration-chocolate-coming-out-wrapper_498928-124.jpg")
						div.content
							div.header Chocolates
					router-link.ui.card(to="/plp/healthDrinks")
						div.image
							img(src="https://thumbs.dreamstime.com/b/health-drink-line-art-cartoon-image-80960712.jpg")
						div.content
							div.header Health Drinks
			
	section#about
			h2.ui.header.teal About us
			p Welcome to Mealecule. Mealecule is founded by Team Popeye. 
			p 
					strong Team Popeye 
					| consists of 
					a(href="mailto:ashwati.menon@publicissapient.com") Ashwati Menon (she/her)
					| - Product Manager, 
					a(href="mailto:apoorvasrinivasan@publicissapient.com") Apoorva Srinivasan(she/her) 
					| - Senior Associate Technology Level 1, 
					a(href="mailto:isha.mehta@publicissapient.com") Isha Mehta (she/her) 
					| - Senior Associate Technology Level 1, 
					a(href="mailto:priya.rawat@publicissapient.com") Priya Rawat (she her)
					| - Associate Technology Level 1 and 
					a(href="mailto:samarth.sharma@publicissapient.com") Samarth Sharma (he/him) 
					| - Intern, Agile Program Management. 
			p Being foodies, the team is happy to bring forth to you our product Mealecule - the Food Molecule and help you in your food shopping experience. We hope that through our customized info-metric MQ on products and user's cart, you would be able to gauge the impact any product you view and purchase will have on your health and bring a change in your lifestyle. 
			p All the best with using our product and do reach out to any of us on Teams/ mail. Stay healthy, stay safe!



</template>
<script>
	document.body.classList.add('home');
import User from '../services/user';
import Highcharts from 'highcharts';

	export default {
		name: 'HomePage',
		mounted(){
				this.getOrders();
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
						for( let k in i.mealeculeQuotientData){
							if (k in chartData.sData)
								chartData.sData[k].push(i.mealeculeQuotientData[k])
							else
								chartData.sData[k] =[i.mealeculeQuotientData[k]]
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
						text: 'mq history'
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
</script>
<style scoped="">
img{ max-width: 100%; max-height: 100%; }
h1.title {
	color: #222;
	font-size: 15vh;
	line-height: 1;
	position: absolute;
	right: 9%;
	top: 50%;
	transform: translate(-20%, -50%);
	width: 394px;
}
h1.title b{
	color:  var(--green) ;
}
section {
	width:100%;
	min-height:70vh;
	padding:  24px;
	overflow: hidden;
}

#orderGraph {
	height:  200px;
	width: 200px;
}
#offer .offers-cards{
	margin-top: 60px;
	display: grid;
	grid-template-columns: repeat(4, 23%);
	justify-content: space-between;
}
.offers-cards .offer-card {
		border: 1px solid #ccc;
		border-radius: 13%  13% 10px 10px;
		box-shadow: 0 0 2px 2px #f4f4f4;
		position: relative;
		padding:  0 15px 20px;
 }
.offers-cards .offer-card .image {
		width: 100px;
		margin: 0 auto;
		border-radius:  50%;
		background-size: contain;
		background-position: center center;
		background-repeat: no-repeat;
		height:  100px;
		position: relative;
		transform:  translate(0%,-50%);
		border: 5px solid #cbcbcb;
}
.offer-card .image.earn-coins {
	background-image: url(../assets/coins.jfif);
}
.offer-card .image.discount {
	background-image: url(../assets/discount.jpg);
}
.offer-card .image.games {
	background-image: url(../assets/game.png);
	background-position: 2px 3px;
}
.offer-card .image.mq {
	background-image: url(../assets/mq.png);
}
.offers-cards .offer-card > p{
		font-size: .8rem;
}
.offers-cards .offer-card .header{
	margin-top: -40px;
	margin-bottom: 20px;
	text-align: center;
}
#categoryList .image{
	display: flex;
	justify-content: center;
	height: 256px;
}
.cta-button{
	position:absolute;
	bottom: 24px;
}
section > .grid{
	height: 100%;
}
button {
	margin-top:  auto;
}
#categories{
	display: grid;
	grid-template-columns: 50% 50%;
	grid-gap: 10px;

}
#categories .cat-images {
	height:  100%;
	background-repeat: no-repeat;
	background-size: cover;
	filter:alpha(opacity=50);
	padding:  24px;
	background-position: center;
}
#categories .header {
	color:  #222;
	background: rgba(255,236,92,.8);
	padding:  5px 10px;
	border-radius: 10px;
	width:  fit-content;
	font-weight: bold;
}
.glutenFree {
	background: url(https://www.picpedia.org/chalkboard/images/gluten-free-foods.jpg) no-repeat;
	grid-row:  1/3;
}
.vegan {
	background: url(https://images.news18.com/ibnlive/uploads/2019/07/vegan.jpg?im=FitAndFill,width=1200,height=675) no-repeat;
}
.childcare {
	background: url(https://cdn.cdnparenting.com/articles/2018/07/743111095-H.jpg) no-repeat;
}

.ui.label.BRONZE{
	color: #f4f4f4;
	background-color: var(--bronze);
}

@media screen and (max-width: 800px){
	
	h1.title {
		right: 0;
		top: 65%;
		transform: none;
		width: 90%;
		font-size: 8vh;
	}
	.ui[class*="two column"].grid>.column:not(.row), .ui[class*="two column"].grid>.row>.column{
		width: 100%;
	}
	.game > .grid {
		display: grid;
		grid-template-columns: 100%;
		align-items: flex-start;
	}
	/*.game .column.ui.image { display: none; }*/

	#categories {
		grid-template-columns: 100%;
	}
	.cta-button{
		position:  static;
	}
	#offer .offers-cards{
		grid-template-columns: 100%;
		gap: 75px 0 ;
	}
	#categoryList .ui.cards{
		flex-flow:  column;
	}
	#categoryList .ui.card{
		width: 256px;
		margin:  24px auto;
	}
}
</style>