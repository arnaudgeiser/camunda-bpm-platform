package org.camunda.bpm.engine.impl.bpmn.parser;

import java.util.List;

import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.PvmActivity;
import org.camunda.bpm.engine.impl.pvm.PvmScope;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.TransitionImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.camunda.bpm.engine.impl.variable.VariableDeclaration;

/**
 * Abstract base class for implementing a {@link BpmnParseListener} without being forced to implement
 * all methods provided, which makes the implementation more robust to future changes.
 *
 * @author ruecker
 */
public class AbstractBpmnParseListener implements BpmnParseListener {

  public void parseProcess(Element processElement, ProcessDefinitionEntity processDefinition) {
  }

  public void parseStartEvent(Element startEventElement, PvmScope scope, ActivityImpl startEventActivity) {
  }

  public void parseExclusiveGateway(Element exclusiveGwElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseInclusiveGateway(Element inclusiveGwElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseParallelGateway(Element parallelGwElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseScriptTask(Element scriptTaskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseServiceTask(Element serviceTaskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseBusinessRuleTask(Element businessRuleTaskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseTask(Element taskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseManualTask(Element manualTaskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseUserTask(Element userTaskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseEndEvent(Element endEventElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseBoundaryTimerEventDefinition(Element timerEventDefinition, boolean interrupting, ActivityImpl timerActivity) {
  }

  public void parseBoundaryErrorEventDefinition(Element errorEventDefinition, boolean interrupting, ActivityImpl activity, PvmActivity nestedErrorEventActivity) {
  }

  public void parseSubProcess(Element subProcessElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseCallActivity(Element callActivityElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseProperty(Element propertyElement, VariableDeclaration variableDeclaration, PvmActivity activity) {
  }

  public void parseSequenceFlow(Element sequenceFlowElement, PvmScope scopeElement, TransitionImpl transition) {
  }

  public void parseSendTask(Element sendTaskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseMultiInstanceLoopCharacteristics(Element activityElement, Element multiInstanceLoopCharacteristicsElement, ActivityImpl activity) {
  }

  public void parseIntermediateTimerEventDefinition(Element timerEventDefinition, ActivityImpl timerActivity) {
  }

  public void parseRootElement(Element rootElement, List<ProcessDefinitionEntity> processDefinitions) {
  }

  public void parseReceiveTask(Element receiveTaskElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseIntermediateSignalCatchEventDefinition(Element signalEventDefinition, ActivityImpl signalActivity) {
  }

  public void parseBoundarySignalEventDefinition(Element signalEventDefinition, boolean interrupting, ActivityImpl signalActivity) {
  }

  public void parseEventBasedGateway(Element eventBasedGwElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseTransaction(Element transactionElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseCompensateEventDefinition(Element compensateEventDefinition, PvmActivity compensationActivity) {
  }

  public void parseIntermediateThrowEvent(Element intermediateEventElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseIntermediateCatchEvent(Element intermediateEventElement, PvmScope scope, ActivityImpl activity) {
  }

  public void parseBoundaryEvent(Element boundaryEventElement, PvmScope scopeElement, ActivityImpl nestedActivity) {
  }

  public void parseIntermediateMessageCatchEventDefinition(Element messageEventDefinition, PvmActivity nestedActivity) {
  }

  public void parseBoundaryMessageEventDefinition(Element element, boolean interrupting, PvmActivity messageActivity) {
  }

}
