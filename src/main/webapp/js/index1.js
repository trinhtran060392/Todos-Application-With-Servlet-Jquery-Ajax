(function($) {
	Backbone.sync = function(method, model, success, error) {
		success();
	}
	var ItemView = Backbone.View.extend({
		
		tagName: 'li',
		events:{
			'click span.delete': 'remove'
		},
		initialize: function(){
			_.bindAll(this,'render','unrender','remove');
			this.model.bind('remove',this.unrender);
			$(this.el).append('<span style="color:black;">'+this.model.get('part1')+' '+this.model.get('part2')+'</span>  &nbsp; <span class="delete" style="cursor:pointer; color:red; font-family:sans-serif;">[delete]</span>');
		},
		unrender : function(){
			$(this.el).remove();
		},
		remove: function(){
			this.model.destroy();
		}
	});
	var ListView = Backbone.View.extend({

		el : $('body'),
		events : {
			"keypress #content1" : "filter"
		},
		initialize : function() {

			_.bindAll(this, 'render', 'filter');
			this.input = this.$("#content1");

			this.render();
		},
		render : function() {

			// $(this.el).append("<input type='text' id ='content1' >");
			// $(this.el).append("<ul></ul>");
		},
		filter : function(e) {
			var value = $("#content1").val();
			if (e.which === 13) {
				$('ul', this.el).append("<li>" + value + "</li>");
				$("#content1").val('');
			}

		}
	});
	var listView = new ListView();
})(jQuery);
