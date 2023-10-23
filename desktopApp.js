const http = require('http');

const options = {
    hostname: 'localhost',
    port: 8080,
    path: '/homework1/number2tree?numVertices=5',
    method: 'GET', // Change to 'POST'
};

const req = http.request(options, (res) => {
  let data = '';

  res.on('data', (chunk) => {
    data += chunk;
  });

  res.on('end', () => {
    console.log('Response data:\n' + data);
  });
});

req.end();
