const { getUserId } = require('../utils')

const Query = {
  me(parent, args, ctx, info) {
    const id = getUserId(ctx)
    return ctx.db.query.user({ where: { id } }, info)
  },
  documentTemplate(parent, args, ctx, info) {
    return ctx.db.query.documentTemplate(args, info)
  },
  documentTemplates(parent, args, ctx, info) {
    return ctx.db.query.documentTemplates(args, info)
  },
  userActionRequests(parent, args, ctx, info) {
    return ctx.db.query.userActionRequests(args, info)
  },
}

module.exports = { Query }
