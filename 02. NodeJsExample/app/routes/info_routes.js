module.exports = function(app, fs) {
  app.get('/info', (req, res) => {
    var url = require('url');
    var url_parts = url.parse(req.url, true);
    var query = url_parts.query;
    // console.log('Name ' + query['name']);
    // console.log('Surname ' + query['surname'])
    fs.appendFile('data.txt', query['name'] + ' ' + query['surname'], 
        function (err) {
        if (err) throw err;
        console.log('Saved!');
        res.send();
    });
  });
};