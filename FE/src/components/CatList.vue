<template lang='pug'>
div.ui.input.field
	input.ui.input(placeholder='search' v-model="searchTerm")
nav.catList(aria-label="category list")
	div.ui.list(v-for="c in catlist" :key="c.id")
		div.item.heading(:class="{'hide':c.fil.length==0}") {{ c.name }}
		router-link.item(:to="{name:'plp', params:{id:cs.id}}" v-for="cs in c.fil" :key="cs.id") {{ cs.name || cs.id }}
</template>
<script>
export default {	
	name:'catList',
	data(){
		return{
			searchTerm : ''
		}
	},
	computed:{
		catlist:function(){
			let vm =this;
			vm.$root.categories.map((i)=>{
				i.fil = i.subcategories;
			});
			if(vm.searchTerm == '') return vm.$root.categories
			return vm.$root.categories.filter((i)=>{
				if(vm.searchTerm.length)
					i.fil = i.subcategories.filter((j)=>{
						return j.id.toLowerCase().startsWith(vm.searchTerm.toLowerCase())
					})
				return i
			})
		}
	}
}
</script>
<style>
.ui.input {
    font-size: 1em;
    margin-bottom: 60px;
}
.catList {
	display:flex;
	justify-content:space-between;
	align-items: flex-start;
	z-index: 0;
}

.catList .ui.list{
	margin: 0;
}
.item.heading{
	color: var(--red);
	font-weight: bold;
	text-transform: capitalize;
}
.item.heading.hide{
	visibility:hidden;
}
@media screen and (max-width: 800px){
	.catList {
		display: grid;
		grid-template-columns: repeat(2,auto);
		gap: 24px;
	}
}
</style>