// Объявляем четыре константы, каждая из констант
// содержит модуль из определенной библиотеки

// для отдачи статики
const express = require('express');
// создаем экземпляр объекта
const app = express();
// для работы с файловым хранилищем
const fs = require('fs');
// для обработки тела post-запросов
const bodyParser = require('body-parser');
// подключаем его к модулю express
app.use(bodyParser.urlencoded({ extended: true }));
// вызываем функцию для обработки маршутов из папки
// routes (конкретно файл index.js, в эту функцию
// передаем объект express и объект файлового хранилища
require('./app/routes')(app, fs);
// говорим, что раздаем папку public
app.use(express.static('public'));
app.listen(80);
console.log("Server started at 80");