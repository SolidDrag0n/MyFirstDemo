/*防止页面元素属性被修改*/
if(window.addEventListener){  
	// 在文本节点的值变更时触发
	window.addEventListener("DOMCharacterDataModified", function(){window.location.reload();}, true);  
	// 在属性名变更时触发
	window.addEventListener("DOMAttributeNameChanged", function(){window.location.reload();}, true);  
	// 在元素名变更时触发
	window.addEventListener("DOMElementNameChanged", function(){window.location.reload();}, true);  
	// 在一个节点作为子节点被插入到另一个节点中时触发
	window.addEventListener("DOMNodeInserted", function(){window.location.reload();}, true);  
	// 在一个节点被直接插入文档中或者通过子树间接插入文档后触发
	window.addEventListener("DOMNodeInsertedIntoDocument", function(){window.location.reload();}, true);  
	// 在节点从其父节点中被移除时触发
	window.addEventListener("DOMNodeRemoved", function(){window.location.reload();}, true);  
	// 在一个节点被直接从文档中删除或通过子树间接从文档中移除之前触发
	window.addEventListener("DOMNodeRemovedFromDocument", function(){window.location.reload();}, true);  
	// 在DOM结构中发生任何变化时触发
	window.addEventListener("DOMSubtreeModified", function(){window.location.reload();}, true);  
}
