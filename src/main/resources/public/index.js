(function() {

	var Model = function() {
		var self = this;
		
		self.initialize = function() {
			self.loadComplete = ko.observable(false);
			/*self.list = ko.observableArray([ {
				id : '123',
				name : 'Joao',
				lastName : 'da Silva',
				phone : '12312312'
			} ]); */
			self.list = ko.observableArray();
			self.load();
		}

		self.load = function() {
			self.loadComplete(false);
			App.get('api/persons').then(function(data) {
				self.list.removeAll();
				for (i = 0; i < data.length; i++){
					self.list.push(data[i]);
				}
				self.loadComplete(true);
			})
		}

	}

	var model = new Model();
	model.initialize();

	ko.applyBindings(model);
})();