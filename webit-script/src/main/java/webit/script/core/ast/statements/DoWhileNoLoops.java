// Copyright (c) 2013-2014, Webit Team. All Rights Reserved.
package webit.script.core.ast.statements;

import webit.script.Context;
import webit.script.core.ast.Expression;
import webit.script.core.ast.Statement;
import webit.script.util.ALU;
import webit.script.util.StatementUtil;

/**
 *
 * @author Zqq
 */
public final class DoWhileNoLoops extends Statement {

    private final Expression whileExpr;
    private final int indexer;
    private final Statement[] statements;

    public DoWhileNoLoops(Expression whileExpr, int indexer, Statement[] statements, int line, int column) {
        super(line, column);
        this.whileExpr = whileExpr;
        this.indexer = indexer;
        this.statements = statements;
    }

    public Object execute(final Context context) {
        final Statement[] stats = this.statements;
        final int preIndex = context.indexer;
        context.indexer = indexer;
        do {
            StatementUtil.executeInverted(stats, context);
        } while (ALU.isTrue(whileExpr.execute(context)));
        context.indexer = preIndex;
        return null;
    }
}
