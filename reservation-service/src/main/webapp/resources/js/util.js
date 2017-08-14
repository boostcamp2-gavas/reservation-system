var extend = function (superClass, def) {
	var extendClass = function extendClass() {
		// Call a parent constructor
		superClass.apply(this, arguments);

		// Call a child constructor
		if (typeof def.init === "function") {
			def.init.apply(this, arguments);
		}
	};

	var ExtProto = function() {};
	ExtProto.prototype = superClass.prototype;

	var extProto = new ExtProto();
	for (var i in def) {
		extProto[i] = def[i];
	}
	extProto.constructor = extendClass;
	extendClass.prototype = extProto;

	return extendClass;
};

module.exports = extend;