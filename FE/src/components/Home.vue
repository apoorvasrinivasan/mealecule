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
						span.label {{ total_coins }} Coins
					div.ui.statistic(v-if="$root.badges")
						span.value 
							i.ui.huge.label.circular.badges(:class="$root.badges")
								i.icon.ui.trophy 
						span.label {{ $root.badges }} Badge
				router-link.ui.button.primary.cta-button(to="/game") Play Game
			div.column.ui.image
				img(:src="require('@/assets/ame.jpg')")
	section#categories.categories
			div.glutenFree.cat-images
				div.ui.header Gluten Free
			div.vegan.cat-images
				div.ui.header Vegan
			div.childcare.cat-images
				div.ui.header Childcare
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
	export default {
		name: 'HomePage',
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
	height:70vh;
	padding:  24px;
	
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
	
}
</style>