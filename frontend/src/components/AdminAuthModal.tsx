
import { useState } from 'react';
import { X } from 'lucide-react';
import { Button } from '@/components/ui/button';

interface AdminAuthModalProps {
  isOpen: boolean;
  onClose: () => void;
  onAuthenticate: () => void;
}

const AdminAuthModal = ({ isOpen, onClose, onAuthenticate }: AdminAuthModalProps) => {
  const [password, setPassword] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // 간단한 인증 (실제로는 더 안전한 방법 사용)
    if (password === 'admin123') {
      onAuthenticate();
    } else {
      alert('비밀번호가 틀렸습니다.');
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-8 max-w-md w-full mx-4 relative">
        <button
          onClick={onClose}
          className="absolute top-4 right-4 text-gray-400 hover:text-gray-600"
        >
          <X className="w-5 h-5" />
        </button>
        
        <div className="text-center mb-6">
          <h2 className="text-2xl font-medium text-gray-900 mb-2">관리자 인증</h2>
          <p className="text-gray-600">
            관리자 페이지에 접근하려면 비밀번호를 입력하세요.
          </p>
        </div>
        
        <form onSubmit={handleSubmit}>
          <div className="mb-6">
            <input
              type="password"
              placeholder="비밀번호를 입력하세요"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
              required
            />
          </div>
          
          <Button 
            type="submit"
            className="w-full bg-amber-700 hover:bg-amber-600 text-white py-3"
          >
            인증하기
          </Button>
        </form>
      </div>
    </div>
  );
};

export default AdminAuthModal;
