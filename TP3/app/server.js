const express = require("express");

const { connectMongo } = require("./services/mongodb");
const { connectRabbitMQ } = require("./services/rabbitmq");
const commandesRoutes = require("./routes/commandes");

const app = express();
const PORT = process.env.PORT || 3001;

app.use(express.json());

app.use("/commandes", commandesRoutes);

app.get("/health", (req, res) => {
  res.json({ status: "OK" });
});

async function start() {
  await connectMongo();
  await connectRabbitMQ();

  app.listen(PORT, () => {
    console.log("Server running on port", PORT);
  });
}

start();