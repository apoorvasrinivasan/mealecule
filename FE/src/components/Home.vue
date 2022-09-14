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
				img(:src="require('@/assets/images/ame.webp')" alt="Play game with popoye")
	section#offer
			h2.ui.header.teal What we offer
			div.offers-cards
				div.offer-card
						div.image.games(aria-hidden="true")
						div.ui.header.tiny Play Games
						p Play a simple single player game to help Popeye win coins and avoid foodie obstacles. Collect interesting badges and unlock exciting benefits, to keep you healthy, fit and fine throughout.
						router-link(to="/game") View More

				div.offer-card
						div.image.earn-coins(aria-hidden="true")
						div.ui.header.tiny Earn Coins
						p Collect B Coins on site by activating your user profile, setting essential body vitals and your peference of MQ to help us serve you better. Redeem these coins and win exciting discounts on all your favourite products.

						router-link(to="/game") View More
				div.offer-card
						div.image.mq
						div.ui.header.tiny(aria-hidden="true") Mealecular Quotient
						p Our personalized info-metrics containing information of quantities (%) of product ingredients such as protein, carbohydrate, fat for all products viewed and added to cart. Sort and filter based on your preferred MQ.
						router-link(to="/myAccount") View More
				div.offer-card
						div.image.discount(aria-hidden="true")
						div.ui.header.tiny Get Discounts
						p Earning coins and interesting badges provides you with amazing discounts on MRP of all products being bought by you.
						p Win 10% off on all products with bronze badge, 15% off with silver badge and 20% off with gold badge.
						router-link(to="/game") View More
	section#categories.categories
			router-link(to="/plp/gluten"  aria-label="go to Gluten category ").glutenFree.cat-images
				div.ui.header Gluten Free
			router-link(to="/plp/vegan" aria-label="go to Vegan category ").vegan.cat-images
				div.ui.header Vegan
			router-link(to="/plp/keto" aria-label="go to Keto category ").childcare.cat-images
				div.ui.header Keto
	section#categoryList
			h2.ui.header.teal Online Supermarket
			div.ui.four.cards
					router-link.ui.card(to="/plp/biscuits")
						div.image
							img(alt="View biscuits products"  v-lazy="require('@/assets/images/cookie.webp')")
						div.content
							div.header Biscuits
					router-link.ui.card(to="/plp/chocolates")
						div.image
							img(alt="View chocolates products"  v-lazy="require('@/assets/images/chocolate.webp')")
						div.content
							div.header Chocolates
					router-link.ui.card(to="/plp/healthDrinks")
						div.image
							img(alt="View healthDrinks products"  v-lazy="require('@/assets/images/healthdrinks.webp')")
						div.content
							div.header Health Drinks
					router-link.ui.card(to="/plp/noodles")
						div.image
							img(alt="View Noodles products"  v-lazy="require('@/assets/images/noodles.webp')")
						div.content
							div.header Noodles
			
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