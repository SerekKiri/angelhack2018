const template = {
  async createCheckboxField(parent, args, ctx, info) {
    return await ctx.db.mutation.createCheckboxField(args)
  },
  async createTextField(parent, args, ctx, info) {
    //console.log(await ctx.db.mutation.createTextField(args))
    //return { id: 'XD' }
    return await ctx.db.mutation.createTextField(args)
  },
  async createMarkdownField(parent, args, ctx, info) {
    return await ctx.db.mutation.createMarkdownField(args)
  },
  async createDocumentTemplate(parent, args, ctx, info) {
    return await ctx.db.mutation.createDocumentTemplate(args)
  }
}

module.exports = { template }
