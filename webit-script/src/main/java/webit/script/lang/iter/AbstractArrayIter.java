// Copyright (c) 2013, Webit Team. All Rights Reserved.
package webit.script.lang.iter;

import webit.script.lang.Iter;

/**
 *
 * @author zqq
 */
public abstract class AbstractArrayIter implements Iter {

    protected final int max;
    protected int _index;

    protected AbstractArrayIter(int max) {
        this._index = -1;
        this.max = max;
    }

    public final boolean isFirst() {
        return _index == 0;
    }

    public final boolean hasNext() {
        return _index < max;
    }

    public final int index() {
        return _index;
    }
}
