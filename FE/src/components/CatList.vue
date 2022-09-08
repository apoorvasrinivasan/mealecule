<template lang='pug'>
nav.catList(aria-label="category list")
	div.ui.list(v-for="c in $root.categories" :key="c.id")
		div.item.heading {{ c.name }}
		router-link.item(:to="{name:'plp', params:{id:cs.id}}" v-for="cs in c.subcategories" :key="cs.id") {{ cs.name || cs.id }}
</template>
<script>
export default {	

	name:'catList'
}
</script>
<style>
.catList{
	display:flex;
	justify-content:space-evenly;
	align-items: flex-start;
}

.ui.list{
	margin: 0;
}
.item.heading{
	color: var(--red);
	font-weight: bold;
	text-transform: capitalize;
}
@media screen and (max-width: 800px){
	.catList {
		flex-wrap:wrap;
		max-height: 100vh;
		flex-flow: column;
		gap: 24px;
	}
}
</style>