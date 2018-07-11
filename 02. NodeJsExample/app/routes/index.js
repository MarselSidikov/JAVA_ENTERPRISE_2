const infoRoutes = require('./info_routes');
module.exports = function(app, fs) {
  infoRoutes(app, fs);
};