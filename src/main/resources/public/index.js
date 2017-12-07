(function() {

	var Person = function(person) {
		person = person || {};
		var self = this;
		self.id = ko.observable(person.id || '');
		self.name = ko.observable(person.name || '');
		self.lastName = ko.observable(person.lastName || '');
		self.email = ko.observable(person.email || '');
		self.phone = ko.observable(person.phone || '');

		return self;
	}

	var Photo = function(photo) {
		photo = photo || {};
		var self = this;
		self.id = ko.observable(photo.id || '');
		self.image = ko.observable(photo.image || '');
		self.encodedImage = ko.computed(function() {
			return 'data:image/jpg;base64,' + this.image()
		}, self);
		return self;
	}

	var Model = function() {
		var self = this;

		self.initialize = function() {
			self.selectedPerson = ko.observable({});
			self.isEditMode = ko.observable(false);
			self.loadComplete = ko.observable(false);
			self.list = ko.observableArray();
			self.load();
			self.photos = ko.observableArray();
			self.photo = ko.observable();
			self.status = ko.observable();
			

		};

		self.load = function() {
			self.loadComplete(false);
			App.get('api/persons').then(function(data) {
				self.list.removeAll();
				for (i = 0; i < data.length; i++) {
					self.list.push(new Person(data[i]));
				}
				self.loadComplete(true);
			});
		};

		self.addPerson = function() {
			self.isEditMode(true);
			self.selectedPerson(new Person());
			self.photos.removeAll();
		};

		self.editPerson = function(item) {
			self.isEditMode(true);
			self.selectedPerson(item);
			self.photos.removeAll();
			var success = function(data) {
				for (i = 0; i < data.length; i++) {
					self.photos.push(new Photo(data[i]));
				}
			}
			App.get('api/photos/person/' + item.id()).then(success, self.showError);

		};
		
		self.deletePerson = function(item) {			
			
			self.selectedPerson(item);			
			var success = function() {
				self.load();
				
			}
			
			App.delete('api/persons/' + item.id()).then(success, self.showError);
			


		};

		self.startTrain = function() {			
			self.selectedPerson({});
			self.isEditMode(false);
			console.log('entrou na funcao de iniciar treino')			
			App.get('api/recognitionconfig/preparation/');	

		};
		
		self.checkStatus = function(){
			self.selectedPerson({});
			self.isEditMode(false);
			console.log(self.status)
			var success = function(data) {
				
				
				alert(data[0].codeStatus == '1' ? 'Processo do ultimo treinamento executado foi concluido' : 'Processando treinamento...')
			}
					
				App.get('api/recognitionconfig').then(success,self.showError);
		};
		
		self.isProcessing = function() {
			console.log(self.status())			
			return self.status()  === '1'
		};
			

		self.cancelEdit = function() {
			self.selectedPerson({});
			self.isEditMode(false);
		};

		self.savePerson = function() {
			var person = self.selectedPerson();
			var success = function() {
				self.load();
				self.isEditMode(false);
			}
			App.post('api/persons', {
				id : person.id() || null,
				name : person.name(),
				lastName : person.lastName(),
				email : person.email(),
				phone : person.phone()
			}).then(success, self.showError);
		};

		self.showError = function(err, text, jqXHR) {
			alert(text);
		};

		self.isUpdate = function() {
			return self.selectedPerson() && self.selectedPerson().id && self.selectedPerson().id();
		};

		self.readPhoto = function(item) {
			console.log('savePhoto');
			var reader = new FileReader();
			reader.onload = function() {
				var arrayBuffer = this.result;
				var array = new Uint8Array(arrayBuffer);
				var b64encoded = self.Uint8ToBase64(array);
				
				$('#foto').val('');
				self.savePhoto(b64encoded);
			}
			reader.readAsArrayBuffer(item);
		}

		self.savePhoto = function(encodedImage) {
			var photo = {
				image : encodedImage,
				person : {
					id : self.selectedPerson().id()
				}
			};
			var success = function() {
				self.photos.push(new Photo({image : encodedImage}));
			};
			console.log(self.selectedPerson().id())
			App.post('api/photos', photo).then(success, self.showError);
		}
		
		self.deletePhoto = function(item) {			
			
				
			var success = function() {
				self.photos.remove(function(photo) {
			        return photo.id() == item.id();
			    });				
			};
			
			App.delete('api/photos/' + item.id()).then(success, self.showError);
			
			

		};

		self.Uint8ToBase64 = function(u8Arr) {
			var CHUNK_SIZE = 0x8000; // arbitrary number
			var index = 0;
			var length = u8Arr.length;
			var result = '';
			var slice;
			while (index < length) {
				slice = u8Arr.subarray(index, Math.min(index + CHUNK_SIZE,
						length));
				result += String.fromCharCode.apply(null, slice);
				index += CHUNK_SIZE;
			}
			return btoa(result);
		}
		return self;
	}

	var model = new Model();
	model.initialize();

	ko.applyBindings(model);
})();