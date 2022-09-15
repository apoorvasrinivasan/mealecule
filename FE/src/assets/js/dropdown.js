import * as $ from 'jquery';

export default{
  a : function ($src) {
    $src.find('a').attr('tabindex',0);
    let getActiveElement = function () {
      if($src.hasClass('active')) {
        // menu is open. check for active item
        let $menu = $src.children('.menu.visible')
        let $item = $menu.children('.item.active');
        // check for submenu
        if($item.hasClass('dropdown') && $item.children('.menu').length){
          let $submenu = $item.children('.menu');
          let $item2 = $submenu.children('.item.active');
          if($item2.length) return $item2;
          else return $item;
        }
        return $item;
      }
      else {
        return $src;
      }
    }
     


    let closeMenu = function($t){
       if($t.hasClass('dropdown')) {
        if($t.hasClass('active')) {
          $t.find('.menu').removeClass('transition hidden visible active').hide();
          $t.removeClass('active visible');
          $t.find('.item').removeClass('active selected');
        }
      }
    }
    let openMenu = function($t){
       if($t.hasClass('dropdown')) {
        if(!$t.hasClass('active')) {
          closeMenu($src.find('.submenu.visible').parent())
          $t.addClass('active visible').attr('aria-expanded','true');
          $t.find('.menu').addClass('transition hidden');
          $t.children('.menu').removeClass('hidden').addClass('transition visible').attr('aria-hidden','false').show();
        }
      }
    }
    
    let moveDown = function ($item){
      if($item.attr('id') == 'dropdown'){
        openMenu($item)
        $item = $item.children('.menu.visible').children('.item').eq(0);
        $item.addClass('active selected');
      }
      else if($item.length > 0) {
        if($item.next().length > 0) {
          $item.removeClass('active selected').next().addClass('active selected');
        }
      }
    }
    let moveUp = function ($item){
      if($item.attr('id') == 'dropdown'){
        closeMenu($item)
        return
      }
      if($item.length > 0) {
        if($item.prev().length > 0) {
          $item.removeClass('active selected').prev().addClass('active selected');
        }
      }
      else{
        $item.addClass('active selected')
      }
    }

    let moveRight = function ($item){
      if($item.hasClass('dropdown')){
        let submenu = $item.children('.menu');
        if(submenu.length == 0) return
        $(submenu).addClass('visible active');
        $(submenu).children('.item').eq(0).addClass('active selected');
      }
    }

    let moveLeft = function ($item){
      if($item.parent('.submenu').length >0){
        let $submenu = $item.parent('.menu');
        $item.removeClass('selected active');
        $submenu.removeClass('visible active');
      }

    }
   
    
    $src.on('blur', function(){
      setTimeout(function(){
        closeMenu($src);
      },300)
    })

    $src.on('click', function(e){
      // $t = getActiveElement();
      let $t = $(e.target)
      openMenu($t);
    });

    $src.on('keydown', function(e){
      let $t = getActiveElement();
      switch(e.keyCode){
        case 40 :{
         e.preventDefault();
         moveDown($t);
         break;
        }
        case 38 :{
         e.preventDefault();
         moveUp($t);
         break;
        }
        case 39 :{
         e.preventDefault();
         moveRight($t);
         break;
        }
        case 37 :{
         e.preventDefault();
         moveLeft($t);
         break;
        }
      }
    });
  }
}
