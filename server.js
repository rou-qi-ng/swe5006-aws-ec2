const express = require('express');
const cors = require('cors');

const app = express();

// Configure CORS to allow requests from the frontend on port 4200
app.use(cors({ origin: 'http://localhost:4200' }));

app.get('/', (req, res) => {
    res.json({
        status: 200,
        message: 'hello world changing',
    });
});

app.listen(8080, () => {
    console.log('server running on port 8080');
});