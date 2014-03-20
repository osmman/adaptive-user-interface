var actualState = event.getProperties()['text'];
var modelName = event.getProperties()['model.name'];
var propertyName = event.getProperties()['model.property'];

client.updateModel(modelName, null, propertyName, actualState);
