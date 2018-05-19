const { getUserId } = require('../utils')

const Query = {
  me(parent, args, ctx, info) {
    const id = getUserId(ctx)
    return ctx.db.query.user({ where: { id } }, info)
  },
  documentTemplate(parent, args, ctx, info) {
    const id = getUserId(ctx)
    return ctx.db.query.documentTemplate({ where: { id } }, info)
  },
}

module.exports = { Query }
