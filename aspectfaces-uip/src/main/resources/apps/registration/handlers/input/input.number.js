var actualState = event.getProperties()['text'];
var actualStateStr = actualState;
var actualStateSet = !(actualState == null || actualState == "");
if (actualStateSet) {
	actualState = parseInt(actualState);
}

var mandatory = false;
try {
	mandatory = event.getProperties()['mandatory'];
} catch (err) {
	// nothing to do
}
mandatory = (mandatory == 'true');

var minValue = null;
try {
	minValue = event.getProperties()['value.min'];
} catch (err) {
	// nothing to do
}
var minValueSet = !(minValue == null || minValue == "");
if (minValueSet) {
	minValue = parseInt(minValue);
}

var maxValue = null;
try {
	maxValue = event.getProperties()['value.max'];
} catch (err) {
	// nothing to do
}
var maxValueSet = !(maxValue == null || maxValue == "");
if (maxValueSet) {
	maxValue = parseInt(maxValue);
}

var regualExpression = event.getProperties()['regularExpression'];
	
var modelName = event.getProperties()['model.name'];
var modelSupportName = event.getProperties()['model.support.name'];
var propertyName = event.getProperties()['model.property'];

client.updateModel(modelName, null, propertyName, actualStateStr);

var error = false;
var errorOld = client.getModelValue(modelSupportName, null, propertyName + ".validation.notify");
errorOld = (errorOld == 'true');

error = error || (mandatory && !actualStateSet);

if (actualStateSet) {
	var re = new RegExp(regualExpression);
	var ok = re.test(actualState);
	error = error || !ok;
	if (ok) {
		error = error || (minValueSet && (actualState < minValue));
		error = error || (maxValueSet && (actualState > maxValue));
	}
}

if (error != errorOld) {
	client.updateModel(modelSupportName, null, propertyName + ".validation.notify", "" + error);
}