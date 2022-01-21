// 测试代码

const fs = require('fs');
const path = require('path');
const http = require('http');

const MIME = {
  '.css': 'text/css',
  '.js': 'application/javascript',
};

function combineFiles(pathnames, callback) {
  const output = [];

  (function next(i, len) {
    if (i < len) {
      fs.readFile(pathnames[i], function (err, data) {
        if (err) {
          callback(err);
        } else {
          output.push(data);
          next(i + 1, len);
        }
      });
    } else {
      callback(null, Buffer.concat(output));
    }
  })(0, pathnames.length);
}

function main(argv) {
  const config = JSON.parse(fs.readFileSync(argv[0], 'utf-8'));
  const root = config.root || '.';
  const port = config.port || 80;

  http
    .createServer(function (request, response) {
      const urlInfo = parseURL(root, request.url);

      combineFiles(urlInfo.pathnames, function (err, data) {
        if (err) {
          response.writeHead(404);
          response.end(err.message);
        } else {
          response.writeHead(200, {
            'Content-Type': urlInfo.mime,
          });
          response.end(data);
        }
      });
    })
    .listen(port);
}

function parseURL(root, url) {
  if (url.indexOf('??') === -1) {
    url = url.replace('/', '/??');
  }

  const parts = url.split('??');
  const base = parts[0];

  const pathnames = parts[1].split(',').map(function (value) {
    return path.join(root, base, value);
  });

  return {
    mime: MIME[path.extname(pathnames[0])] || 'text/plain',
    pathnames: pathnames,
  };
}
main(process.argv.slice(2));
