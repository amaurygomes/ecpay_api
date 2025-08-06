# ECPay - API de Pagamentos

![API Status](https://img.shields.io/badge/API-Online-brightgreen)
![Gateway](https://img.shields.io/badge/Gateway-Mercado_Pago-cyan)

API REST para processamento de pagamentos, projetada para se integrar com múltiplos gateways. Atualmente, oferece suporte para pagamentos PIX através do **Mercado Pago** e outros gateways.

Também faz o gerenciamento de cadrastro e atualização de usuarios através de Roles.

Atualiza status de usuário em Plataforma externa com base nos status de pagamento utilizando tarefas agendadas ou através de respostas de webhooks.

## URL Base

Todas as URLs mencionadas nesta documentação são relativas à seguinte URL base:
`https://api.seudominio.com.br`

## Autenticação

Todas as requisições à API devem ser autenticadas. Envie o seu token de acesso no cabeçalho `Authorization` em todas as chamadas.

**Exemplo de Cabeçalho:**
```
Authorization: Bearer <SEU_TOKEN_JWT_AQUI>
```
As requisições sem um token válido resultarão num erro `401 Unauthorized`.

---

## Endpoints da API

### 💳 Gateway de Pagamento

Os endpoints a seguir interagem com vários gateways por enquanto apenas o Mercado Pago.

#### 1. Gerar Pagamento PIX

Cria uma nova cobrança PIX que pode ser paga através de um QR Code ou código "copia e cola".

* **`POST /api/payment/generate_pix/mercadopago`**

##### Corpo da Requisição (Request Body)

| Campo | Tipo | Descrição | Obrigatório |
| :--- | :--- | :--- | :--- |
| `userId` | `string` (UUID) | ID do utilizador para quem o pagamento está a ser gerado. | Sim |
| `billingCycle` | `string` | Ciclo de faturação. Valores possíveis: `MONTHLY`, `DAILY`. | Sim |

* **Exemplo de Corpo:**
    ```json
    {
      "userId": "e5adce7a-ab34-47be-9285-5289b45e4a95",
      "billingCycle": "MONTHLY"
    }
    ```

##### Resposta de Sucesso (`200 OK`)

| Campo | Tipo | Descrição |
| :--- | :--- | :--- |
| `transactionId` | `string` (UUID) | ID interno único gerado pela nossa API para esta transação. |
| `qrCodeBase64` | `string` | Imagem do QR Code codificada em Base64. Pode ser usada para exibir o QR Code diretamente numa interface (`<img src="data:image/png;base64,..." />`). |
| `qrCodeText` | `string` | O código "PIX Copia e Cola" que o utilizador pode usar na aplicação do seu banco. |

* **Exemplo de Resposta:**
    ```json
    {
      "transactionId": "05e48ed8-86f4-4c83-94be-08fe75cd629e",
      "qrCodeBase64": "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAAABGdBTUEAALGPC/xhBQ...",
      "qrCodeText": "000201267...5913NOME DO RECEBEDOR..."
    }
    ```

##### Respostas de Erro Comuns

* `400 Bad Request`: O corpo da requisição é inválido ou faltam campos obrigatórios.
* `401 Unauthorized`: O token de autenticação (`Bearer Token`) não foi fornecido ou é inválido.
* `500 Internal Server Error`: Ocorreu um erro inesperado no servidor ao tentar processar o pagamento.

---

## 🔔 Nota sobre Status de Pagamento

O status de um pagamento (ex: de `PENDING` para `APPROVED`) é atualizado de forma **assíncrona**.

Após a criação de um PIX, a nossa API recebe notificações (webhooks) do gateway de pagamento sempre que o status muda. Para obter o status atualizado de uma transação, recomenda-se consultar um endpoint específico de status da transação (se disponível) ou garantir que a sua aplicação cliente possa reagir a eventos de atualização enviados pelo nosso sistema.

## ✍️ Contato

* **Autor:** Amaury Gomes Ibanez
* **LinkedIn:** `https://www.linkedin.com/in/amaurygomes/`
* **GitHub:** `https://github.com/amaurygomes`
