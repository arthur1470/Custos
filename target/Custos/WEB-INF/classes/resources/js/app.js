$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-menu, .js-corpo').toggleClass('is-toggled');
		event.preventDefault();
	});	
});
