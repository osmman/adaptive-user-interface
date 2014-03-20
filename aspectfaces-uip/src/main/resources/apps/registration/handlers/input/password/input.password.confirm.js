var actualState = event.getProperties()['text'];
var actualStateSet = !(actualState == null || actualState == "");

var modelName = event.getProperties()['model.name'];
var modelSupportName = event.getProperties()['model.support.name'];
var propertyName = event.getProperties()['model.property'];
var propertyNameConfirm = propertyName + '.confirmation';

client.updateModel(modelSupportName, null, propertyNameConfirm, actualState);

var error = false;
var errorOld = client.getModelValue(modelSupportName, null, propertyNameConfirm + ".validation.notify");

errorOld = (errorOld == 'true');

var confirmPass = client.getModelValue(modelName, null, propertyName);
var confirmPassSet = !(confirmPass == null || confirmPass == "");
if (!(actualStateSet == confirmPassSet && actualStateSet == false)) {
	error = error || ((actualStateSet != confirmPassSet) || (confirmPass != actualState));
}

if (error != errorOld) {
	client.updateModel(modelSupportName, null, propertyNameConfirm + ".validation.notify", "" + error);
}
