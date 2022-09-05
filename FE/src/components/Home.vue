<template lang="pug">
div.home-page
	h1.title How 
		b healthy 
		| is your cart
	section#game.game
		div.ui.two.column.grid
			div.column
				h2.ui.header.teal Current Progress
				div.ui.statistic
					span.value 
						i.ui.huge.label.circular.yellow {{ $root.total_coins }}
					span.label Coins
				br
				button.ui.button.primary Play Game
			div.column.ui.image
				img(src="https://s3-us-east-2.amazonaws.com/maryville/wp-content/uploads/2021/03/04131433/MVU-BFADM-2020-Q4-Skyscraper-Future-of-Video-Games-Trends-Technology-Types-header-v2-1000x523.jpg")
	section#categories.categories
			div.glutenFree.cat-images
				div.ui.header Gluten Free
			div.vegan.cat-images
				div.ui.header Vegan
			div.childcare.cat-images
				div.ui.header Childcare



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

</style>