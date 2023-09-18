Feature: Récupérer un client par ID et tous les clients

  Scenario: Récupérer un client existant
    Given creer un utilisateur avec comme nom "assane" prenom "doumbouya" et email "doumzoro@gmail.com"
    When on recupere le client creer ou lu
    Then le client creer ou lu est retourné avec le code de statut 200


  Scenario: Récupérer la liste de tous les clients
    Given creer plusieurs clients
    When l'utilisateur demande la liste de tous les clients
    Then une liste de clients est retournée avec le code de statut 200