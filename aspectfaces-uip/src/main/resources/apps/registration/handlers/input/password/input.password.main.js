var actualState = event.getProperties()['text'];
var actualStateSet = !(actualState == null || actualState == "");

var mandatory = false;
try {
	mandatory = event.getProperties()['mandatory'];
	server.logMessage('mandatory ' + mandatory);
} catch (err) {
	// nothing to do
}
mandatory = (mandatory == 'true');

var minLength = null;
try {
	minLength = event.getProperties()['length.min'];
	server.logMessage('minLength ' + minLength);
} catch (err) {
	// nothing to do
}
var minLengthSet = !(minLength == null || minLength == "");
if (minLengthSet) {
	minLength = parseInt(minLength);
}

var maxLength = null;
try {
	maxLength = event.getProperties()['length.max'];
	server.logMessage('maxLength ' + maxLength);
} catch (err) {
	// nothing to do
}
var maxLengthSet = !(maxLength == null || maxLength == "");
if (maxLengthSet) {
	maxLength = parseInt(maxLength);
}

var passwordConfirm = null;
try {
	passwordConfirm = event.getProperties()['password.confirmation'];
	server.logMessage('passwordConfirm ' + passwordConfirm);
} catch (err) {
	// nothing to do
}
passwordConfirm = (passwordConfirm == 'true');

var modelName = event.getProperties()['model.name'];
var modelSupportName = event.getProperties()['model.support.name'];
var propertyName = event.getProperties()['model.property'];

client.updateModel(modelName, null, propertyName, actualState);

var error = false;
var errorOld = client.getModelValue(modelSupportName, null, propertyName + ".validation.notify");
errorOld = (errorOld == 'true');

error = error || (mandatory && !actualStateSet);

if (actualStateSet) {
	error = error || (minLengthSet && (actualState.length < minLength));
	error = error || (maxLengthSet && (actualState.length > maxLength));
}

if (error != errorOld) {
	client.updateModel(modelSupportName, null, propertyName + ".validation.notify", "" + error);
}

if (passwordConfirm) {

	var errorC = false;
	var errorCOld = client.getModelValue(modelSupportName, null, propertyName + ".confirmation.validation.notify");

	errorCOld = (errorCOld == 'true');

	var confirmPass = client.getModelValue(modelSupportName, null, propertyName + ".confirmation");
	var confirmPassSet = !(confirmPass == null || confirmPass == "");
	
	if (!(actualStateSet == confirmPassSet && actualStateSet == false)) {
		errorC = errorC || ((actualStateSet != confirmPassSet) || (confirmPass !== actualState));
	}

	if (errorC != errorCOld) {
		client.updateModel(modelSupportName, null, propertyName + ".confirmation.validation.notify", "" + errorC);
	}
}

