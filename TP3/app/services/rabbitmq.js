const amqp = require("amqplib");

let channel;
const QUEUE = "commandes";

async function connectRabbitMQ() {
  const conn = await amqp.connect("amqp://admin:admin@rabbitmq");
  channel = await conn.createChannel();
  await channel.assertQueue(QUEUE, { durable: true });

  console.log("RabbitMQ connected");
}

function publierCommande(commande) {
  channel.sendToQueue(
    QUEUE,
    Buffer.from(JSON.stringify(commande)),
    { persistent: true }
  );
}

module.exports = { connectRabbitMQ, publierCommande };