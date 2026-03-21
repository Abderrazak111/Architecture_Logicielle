const mongoose = require("mongoose");

const CommandeSchema = new mongoose.Schema({
  produit: String,
  quantite: Number,
  client: String,
  statut: { type: String, default: "en_attente" },
  date: { type: Date, default: Date.now }
});

const Commande = mongoose.model("Commande", CommandeSchema);

async function connectMongo() {
  await mongoose.connect("mongodb://mongodb:27017/boutique");
  console.log("MongoDB connected");
}

module.exports = { connectMongo, Commande };