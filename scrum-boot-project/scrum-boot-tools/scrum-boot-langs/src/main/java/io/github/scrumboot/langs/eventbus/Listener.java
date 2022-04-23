package io.github.scrumboot.langs.eventbus;

import java.util.EventListener;

/**
 * @author Bing D. Yee
 * @since 2022/04/19
 */
public interface Listener<E extends DomainEvent> extends EventListener {

    /**
     *  处理事件
     *
     * @param event 领域事件
     */
    void onDomainEvent(E event);

}
