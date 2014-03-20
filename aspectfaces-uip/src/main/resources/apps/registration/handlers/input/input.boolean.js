var actualState = event.getProperties()['item'];
var actualStateSet = !(actualState == null || actualState == "");

if (!actualStateSet) {
	actualState = event.getProperties()['state'];
	actualStateSet = !(actualState == null || actualState == "");
}

var mandatory = false;
try {
	mandatory = event.getProperties()['mandatory'];
} catch (err) {
	// nothing to do
}
mandatory = (mandatory == 'true');

var modelName = event.getProperties()['model.name'];
var modelSupportName = event.getProperties()['model.support.name'];
var propertyName = event.getProperties()['model.property'];

client.updateModel(modelName, null, propertyName, actualState);

var error = false;
var errorOld = client.getModelValue(modelSupportName, null, propertyName + ".validation.notify");
errorOld = (errorOld == 'true');

error = error || (mandatory && !actualStateSet);

if (error != errorOld) {
	client.updateModel(modelSupportName, null, propertyName + ".validation.notify", "" + error);
}
