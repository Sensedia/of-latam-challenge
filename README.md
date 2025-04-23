# Desafio Técnico

## 🧠 Contexto

O Banco Central do Chile está implementando um sistema centralizado chamado Diretório de Participantes, responsável por registrar, monitorar e comunicar mudanças de estado de organizações participantes do ecossistema financeiro (Bancos, Fintechs, etc.).

Cada organização possui os seguintes atributos principais:

* organisationId
* organisationName
* status – valores definidos por um enum OrganisationState
* subStatus – valores adicionais de detalhamento do estado

Quando uma organização tem seu status alterado de "ativo" para "inativo", e o subStatus é "Scheduled Maintenance", todos os demais participantes ativos devem ser notificados sobre essa alteração.

## 🎯 Objetivo

1. Implementar uma funcionalidade que:

* Detecta se uma organização está tendo o status alterado de ativo para inativo.
* Verifica se o subStatus é "Scheduled Maintenance".
* Recupera todos os outros participantes ativos.
* Notifica cada um deles sobre a alteração.

2. A notificação deve conter:

* Data e hora do evento
* Tipo do evento (STATUS_CHANGED)
* Dados da organização (ID, Nome, Status novo, Substatus)

3. O envio da notificação pode ser feito de forma:

* Síncrona ou assíncrona
* Como simples saída no console (System.out.println), simulando o envio real