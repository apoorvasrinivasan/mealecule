<template lang="pug">
div.home-page
	h1.title How 
		b healthy 
		| is your cart
	section#game.game
		div.ui.two.column.grid
			div.column
				h2.ui.header.teal Current Progress
				div.ui.statistics(v-if="$root.total_coins && $root.badges")
					div.ui.statistic
						span.value 
							i.ui.huge.label.circular.bCoins $
						span.label {{ $root.total_coins }} Coins
					div.ui.statistic(v-if="$root.badges")
						span.value 
							i.ui.huge.label.circular.badges(:class="$root.badges")
								i.icon.ui.trophy 
						span.label {{ $root.badges }} Badge
				p.ui.message.warning(v-else) You have no coins. Play game to earn coins and get discounts
				div#orderGraph(v-if="orderHistory.length>0")
				router-link.ui.button.primary.cta-button(to="/game" v-if="$root.total_coins") Play Game
				router-link.ui.button.primary.cta-button(to="/game" v-else) Earn some coins and badges
			div.column.ui.image
				img(:src="require('@/assets/ame.webp')" alt="Play game with popoye")
	section#offer
			h2.ui.header.teal What we offer
			div.offers-cards
				div.offer-card
						div.image.games(aria-hidden="true")
						div.ui.header.tiny Play Games
						p Play a simple single player game to help Popeye win coins and avoid foodie obstacles midway. Also collect interesting badges and unlock exciting benefits, tto help you purchase your favourite products on site.
						router-link(to="/game") View More

				div.offer-card
						div.image.earn-coins(aria-hidden="true")
						div.ui.header.tiny Earn Coins
						p Keep collecting BCoins on site by activating your user profile, setting essential body vitals and your peference of MQ to help us serve you better. Redeem these coins during purchase and win exciting discounts on all your favourite products.
						router-link(to="/game") View More
				div.offer-card
						div.image.mq
						div.ui.header.tiny(aria-hidden="true") Mealecular Quotient
						p Understand the impact of buying a product by judging its Mealecular Quotient (MQ) - an info-metrics containing information in a visual representation, of quantities (%) of protein, carbohydrate, fat, fiber and caloric values for all products viewed and added to your cart. Sort and filter based on your preferred MQ.
						router-link(to="/myAccount") View More
				div.offer-card
						div.image.discount(aria-hidden="true")
						div.ui.header.tiny Get Discounts
						p End of a level in our game helps you win coins achievement badges. Earning coins leads to earning fun and interesting badges which would provide amazing discounts on MRP of all products being bought by you. Bronze badge (achieved at 100 coins) gives you 5% discount on MRP of products
						router-link(to="/game") View More
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
							img(alt="View biscuits products"  v-lazy="require('@/assets/cookie.webp')")
						div.content
							div.header Biscuits
					router-link.ui.card(to="/plp/chocolates")
						div.image
							img(alt="View chocolates products"  v-lazy="require('@/assets/chocolate.webp')")
						div.content
							div.header Chocolates
					router-link.ui.card(to="/plp/healthDrinks")
						div.image
							img(alt="View healthDrinks products"  v-lazy="require('@/assets/healthdrinks.webp')")
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
<script src="@/assets/js/home.js">
	export default {
	name: 'HomePage',
}
</script>
<style scoped="" src="@/assets/css/home.css"></style>