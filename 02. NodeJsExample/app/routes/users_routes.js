bodyParser = require('body-parser').json();

// описываем фунцию для обработки post-запроса на url /users
module.exports = function (app, fs) {
    app.post('/users', bodyParser, function (request, response) {
        // вытаскиваю тело в формате JSON
        var body = request.body;
        console.log(body);
        // записываю его в файл
        fs.appendFile('data.txt', body.name + ' ' + body.surname + '\n',
            function (err) {
                if (err) throw err;
                console.log('Saved!');
                response.redirect("/html/index.html");
            });
    });
    app.get('/users', function (request, response) {
        fs.readFile('data.txt', 'utf-8', function(err, data) {
           var lines = data.split('\n');

           var result = [];
           for (var i = 0; i < lines.length; i++) {
               result.push({'name' : lines[i].split(' ')[0],
                   'surname': lines[i].split(' ')[1]});
           }
            response.setHeader('Content-Type', 'application/json');
            response.send(JSON.stringify(result));
        });
    });
};