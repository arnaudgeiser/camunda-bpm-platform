package org.camunda.bpm.engine.impl.bpmn.behavior;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.camunda.bpm.engine.impl.ProcessEngineLogger;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;


/**
 * Helper class for bpmn constructs that allow clojure delegation.
 *
 * This class will lazily instantiate the referenced classes when needed at runtime.
 *
 * @author Arnaud Geiser
 */
public class ClojureDelegateActivityBehavior extends AbstractBpmnActivityBehavior {

  protected static final BpmnBehaviorLogger LOG = ProcessEngineLogger.BPMN_BEHAVIOR_LOGGER;

  private final String expression;
  private final String resultVariable;

  public ClojureDelegateActivityBehavior(String expression, String resultVariable) {
    this.expression = expression;
    this.resultVariable = resultVariable;
  }

  // Activity Behavior
  @Override
  public void execute(final ActivityExecution execution) throws Exception {

    this.executeWithErrorPropagation(execution, () -> {
      String[] result = expression.split("/");
      String ns = result[0];
      String var = result[1];
      IFn fn = Clojure.var(ns, var);
      Object value = fn.invoke(execution);
      if (resultVariable != null) {
        execution.setVariable(resultVariable, value);
      }
      leave(execution);
      return null;
    });
  }

}
